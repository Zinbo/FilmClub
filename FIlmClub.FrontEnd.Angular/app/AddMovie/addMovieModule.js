import angular from "angular";
import addMovieComponent from './addMovieComponent';
import addMovieService from './addMovieService';
import getBackEndServiceUrl from '../Common/getBackEndServiceUrl';

export default name = "addMovieModule";

angular.module(name, [])
    .service('addMovieService', addMovieService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("addMovie", addMovieComponent);