import angular from "angular";
import navigationModule from './navigation/navModule';
import addMovieModule from './movies/add/addMovieModule';
import alertsComponent from './alerts/alertsComponent';
import viewMoviesModule from "./movies/view/viewMoviesModule";

const name = "app";

angular.module(name, [navigationModule, addMovieModule, viewMoviesModule])
    .component('alerts',alertsComponent);
