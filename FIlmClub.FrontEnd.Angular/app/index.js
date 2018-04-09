import angular from "angular";
import navigationModule from './Navigation/navModule';
import addMovieModule from './AddMovie/addMovieModule';

const name = "app";

console.log("In Module")

angular.module(name, [navigationModule, addMovieModule]);
