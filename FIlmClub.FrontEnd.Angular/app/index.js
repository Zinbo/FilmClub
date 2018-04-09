import angular from "angular";
import navigationModule from './navigation/navModule';
import addMovieModule from './movies/add/addMovieModule';
import alertsComponent from './alerts/alertsComponent';

const name = "app";

angular.module(name, [navigationModule, addMovieModule])
    .component('alerts',alertsComponent);
