'use strict';
var gulp = require('gulp');
var gutil = require('gulp-util');
var del = require('del');
var uglify = require('gulp-uglify');
var gulpif = require('gulp-if');
var notify = require('gulp-notify');
var buffer = require('vinyl-buffer');
var argv = require('yargs').argv;
// sass
var sass = require('gulp-sass');
var postcss = require('gulp-postcss');
var autoprefixer = require('autoprefixer');
var sourcemaps = require('gulp-sourcemaps');
// BrowserSync
var browserSync = require('browser-sync');
// js
var watchify = require('watchify');
var browserify = require('browserify');
var source = require('vinyl-source-stream');
// image optimization
var imagemin = require('gulp-imagemin');
// linting
// var jshint = require('gulp-jshint');
// var stylish = require('jshint-stylish');
var eslint = require('gulp-eslint');
// testing/mocha
var babelify = require('babelify');
var browserify_shim = require('browserify-shim');
var Server = require('karma').Server;
// gulp build --production
var production = !!argv.production;
// determine if we're doing a build
// and if so, bypass the livereload
var build = argv._.length ? argv._[0] === 'build' : false;
var watch = argv._.length ? argv._[0] === 'watch' : true;

// ----------------------------
// Error notification methods
// ----------------------------
var handleError = function (task) {
    return function (err) {
        notify.onError({
            message: task + ' failed, check the logs..',
            sound: false
        })(err);

        gutil.log(gutil.colors.bgRed(task + ' error:'), gutil.colors.red(err));
    };
};
// --------------------------
// CUSTOM TASK METHODS
// --------------------------
var tasks = {
    // --------------------------
    // Delete build folder
    // --------------------------
    clean: function () {
        del.sync(['build/']);
    },
    // --------------------------
    // Copy static assets
    // --------------------------
    assets: function () {
        return gulp.src('./app/shared/**/*')
            .pipe(gulp.dest('build/shared/'));
    },
    // --------------------------
    // HTML
    // --------------------------
    // html templates (when using the connect server)
    templates: function () {
        gulp.src('./app/**/*.html')
            .pipe(gulp.dest('build/'));
    },
    // --------------------------
    // SASS (libsass)
    // --------------------------
    sass: function () {
        return gulp.src('./app/**/*.scss')
            // sourcemaps + sass + error handling
            .pipe(gulpif(!production, sourcemaps.init()))
            .pipe(sass({
                sourceComments: !production,
                outputStyle: production ? 'compressed' : 'nested'
            }))
            .on('error', handleError('SASS'))
            // generate .maps
            .pipe(gulpif(!production, sourcemaps.write({
                'includeContent': false,
                'sourceRoot': '.'
            })))
            // autoprefixer
            .pipe(gulpif(!production, sourcemaps.init({
                'loadMaps': true
            })))
            .pipe(postcss([autoprefixer({ browsers: ['last 2 versions'] })]))
            // we don't serve the source files
            // so include scss content inside the sourcemaps
            .pipe(sourcemaps.write({
                'includeContent': true
            }))
            // write sourcemaps to a specific directory
            // give it a file and save
            .pipe(gulp.dest('build/'));
    },
    // --------------------------
    // Browserify
    // --------------------------
    browserify: function () {
        var bundler = browserify('./app/index.js', {
            debug: !production,
            cache: {},
            shim: {
                jquery: 'global:$'
            }
        })
            .transform(babelify)
            .transform(browserify_shim);

        // determine if we're doing a build
        // and if so, bypass the livereload
        build = argv._.length ? argv._[0] === 'build' : false;
        if (watch) {
            bundler = watchify(bundler);
        }
        var rebundle = function () {
            return bundler.bundle()
                .on('error', handleError('Browserify'))
                .pipe(source('bundle.js'))
                .pipe(gulpif(production, buffer()))
                .pipe(gulpif(production, uglify()))
                .pipe(gulp.dest('build/'));
        };
        bundler.on('update', rebundle);
        return rebundle();
    },
    // --------------------------
    // linting
    // --------------------------
    lintjs: function () {
        return gulp.src([
            'gulpfile.js',
            './app/index.js',
            './app/**/*.js'
        ]).pipe(eslint())
            .pipe(eslint.format())
            .pipe(eslint.failAfterError());

    },
    // --------------------------
    // Optimize asset images
    // --------------------------
    optimize: function () {
        return gulp.src('./app/assets/**/*.{gif,jpg,png,svg}')
            .pipe(imagemin({
                progressive: true,
                svgoPlugins: [{ removeViewBox: false }],
                // png optimization
                optimizationLevel: production ? 3 : 1
            }))
            .pipe(gulp.dest('./app/assets/'));
    },
    // --------------------------
    // Testing with karma + jasmine
    // --------------------------
    test: function (done) {
        new Server({
            configFile: __dirname + '/karma.conf.js',
            singleRun: true
        }, done).start();
    },


};

gulp.task('browser-sync', function () {
    browserSync({
        server: {
            baseDir: "./build"
        },
        port: process.env.PORT || 3000
    });
});

gulp.task('reload-sass', ['sass'], function () {
    browserSync.reload();
});
gulp.task('reload-js', ['browserify'], function () {
    browserSync.reload();
});
gulp.task('reload-templates', ['templates'], function () {
    browserSync.reload();
});

// --------------------------
// CUSTOMS TASKS
// --------------------------
gulp.task('clean', tasks.clean);
// for production we require the clean method on every individual task
var req = build ? ['clean'] : [];
// individual tasks
gulp.task('templates', req, tasks.templates);
gulp.task('assets', req, tasks.assets);
gulp.task('sass', req, tasks.sass);
gulp.task('browserify', req, tasks.browserify);
gulp.task('lint:js', tasks.lintjs);
gulp.task('optimize', tasks.optimize);
gulp.task('test', tasks.test);

// --------------------------
// DEV/WATCH TASK
// --------------------------
gulp.task('watch', ['assets', 'templates', 'sass', 'browserify', 'browser-sync', 'test'], function () {

    // --------------------------
    // watch:sass
    // --------------------------
    gulp.watch('./app/**/*.scss', ['reload-sass']);

    // --------------------------
    // watch:js
    // --------------------------
    gulp.watch('./app/**/*.js', ['lint:js', 'reload-js', 'test']);

    // --------------------------
    // watch:html
    // --------------------------
    gulp.watch('./app/**/*.html', ['reload-templates']);

    gutil.log(gutil.colors.bgGreen('Watching for changes...'));
});

// build task
gulp.task('build', [
    'clean',
    'templates',
    'assets',
    'sass',
    'browserify'
]);

gulp.task('default', ['watch']);

// gulp (watch) : for development and livereload
// gulp build : for a one off development build
// gulp build --production : for a minified production build