module.exports = function(config) {
    config.set({
        basePath: './',
        frameworks: ['browserify', 'jasmine'],
        browsers: ['PhantomJS'],
        files: ['test/**/*.spec.js'],
        preprocessors: {
            'test/**/*.js': ['browserify']
        },
        browserify: {
            debug: true,
            transform: ['babelify']
        }
    });
};