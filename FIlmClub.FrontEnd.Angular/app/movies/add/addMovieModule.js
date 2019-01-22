import angular from "angular";
import addMovieComponent from './addMovieComponent';
import movieService from '../shared/movieService';
import theMovieDbService from './theMovieDbService';
import getBackEndServiceUrl from '../../common/getBackEndServiceUrl';

export default name = "addMovieModule";

angular.module(name, [])
    .service('movieService', movieService)
    .service('theMovieDbService', theMovieDbService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("addMovie", addMovieComponent);