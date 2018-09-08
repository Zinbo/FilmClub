import angular from "angular";
import addMovieComponent from './addMovieComponent';
import movieService from './movieService';
import theMovieDbService from './theMovieDbService';
import getBackEndServiceUrl from '../../common/getBackEndServiceUrl';
import uibootstrap from 'angular-ui-bootstrap';

export default name = "addMovieModule";

angular.module(name, [uibootstrap])
    .service('movieService', movieService)
    .service('theMovieDbService', theMovieDbService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("addMovie", addMovieComponent);