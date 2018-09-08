# FilmClub

## Goal
The goal of this application is to compare the different server side and front end frameworks/languages/libraries/tools.

## How it works
The way the app is developed:
```bash
git flow feature start <feature name> #We do this first thing when developing a new feature, this will create a new branch
git add . #Add the files when we want to do a local commit
git commit -m "message add [branch ch<storyNumber from clubhouse.io>]" #This will commit the ticket locally and will associate it with the clubhouse ticket
git push #When we are ready for it to go to repository
git flow feature finish <feature name> #When we are done with a feature we merge it back into development
git flow feature publish <feature name> #Commit development to GitHub
```
When we want to go to heroku (When we're happy with a feature - either part way through or finished)
```bash
git subtree push --prefix <folder name> <git remote URL tag> master #This pushes a subtree (so just a sub-folder <folder_name>, not the whole repo as this contains multiple apps, to the master branch of the heroku remote URL tag)
```
Heroku knows how to start each application because each folder contains a `procfile`.
The procfile details how to start the application

The stories for this project can be found at [here](https://app.clubhouse.io/zinbo/stories/workspace/2/everything).

## Back-End

### FilmClub.Java.Dropwizard
This is the API for the application written in Java using Dropwizard. It defines a clean RESTful interface and is used by the front end. It is designed to be generic but this is due to the application not being overly complex.

Because it isn't complex there is no need for an application service.

The configuration for this app can be found [here](https://dashboard.heroku.com/apps/filmclub-java-dropwizard).  
The application can be found here [here](https://filmclub-java-dropwizard.herokuapp.com).  
An example call can be found [here](https://filmclub-java-dropwizard.herokuapp.com/person/1).  
A swagger page still needs to be added. 

In order to push this application and deploy it to heroku you can run the `push-dropwizard.bat` file which is in the root of the project. This runs
`git subtree push --prefix FilmClub.Java.Dropwizard heroku-java-dropwizard master` where 
`heroku-java-dropwizard = https://git.heroku.com/filmclub-java-dropwizard.git` and `master` is the only branch in the heroku repo.

The procfile for this application starts the jar by doing the following:   
`web: java  $JAVA_OPTS -Ddw.server.applicationConnectors[0].port=$PORT -jar target/filmclub.java.dropwizard-1.0-SNAPSHOT.jar server config.yaml`
`$PORT` is a pre-populated env var by heroku which we use to tell dropwizard what port to run on.

## Front-End

### FilmClub.FrontEnd.Angular
This is the Angular front end for the application using Angular 1.6. Heroku does not support the hosting of just static files so in order to host this angular application we have a lightweight node server which just returns the index page no matter what you resource you ask for:
`res.sendfile('./build/index.html');`

The configuration for this app can be found [here](https://dashboard.heroku.com/apps/filmclub-frontend-angular-1).   
The app can be found [here](https://filmclub-frontend-angular-1.herokuapp.com/).

In order to push this application and deploy it to heroku you can run the `push-angular1.bat` file which is in the root of the project. This runs:
`git subtree push --prefix FIlmClub.FrontEnd.Angular heroku-frontend-angular-1 master` where    
`heroku-frontend-angular-1 = https://git.heroku.com/filmclub-frontend-angular-1.git` and `master` is the only branch in the heroku repo.

The procfile for this application runs the npm task `start` by doing the following:   
`web: npm start`, where `start` runs `node server` which runs `server.js` which starts the node server.  Heroku also runs the npm task `postinstall` which runs `gulp build` after heroku does its build. 
This is so the `index.html` file is built. The two tasks can be found in the `package.json` file:

```javascript
...
"scripts": {
    "start": "node server",
    "postinstall": "gulp build"
},
...
```
#### To Start Locally
Run `npm start` and it will be deployed on port 8080

#### Design Decisions
Couldn't get protractor working :(
