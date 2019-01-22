exports.config = {
    specs: ['test/protractor/**/*spec.js'],
    // The location of the selenium standalone server .jar file, relative
    // to the location of this config. If no other method of starting selenium
    // is found, this will default to
    // node_modules/protractor/selenium/selenium-server...
    seleniumServerJar: 'C:/Users/super/AppData/Roaming/npm/node_modules/protractor/node_modules/webdriver-manager/selenium/selenium-server-standalone-3.5.2.jar',

    // The port to start the selenium server on, or null if the server should
    // find its own unused port.
    seleniumPort: 4444,
    framework: 'jasmine2',
    scrollRestoreTechnique: 'cookie'
};