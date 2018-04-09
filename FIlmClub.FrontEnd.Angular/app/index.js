import angular from "angular";
import navigationModule from './navigation/navModule';
import addMovieModule from './movies/add/addMovieModule';

const name = "app";

angular.module(name, [navigationModule, addMovieModule]);
