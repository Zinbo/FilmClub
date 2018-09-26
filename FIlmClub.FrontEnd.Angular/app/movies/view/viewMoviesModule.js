import angular from "angular";
import viewMoviesComponent from './viewMoviesComponent';
import movieService from '../shared/movieService';
import getBackEndServiceUrl from '../../common/getBackEndServiceUrl';
import deleteMovieComponent from '../delete/deleteComponent';

export default name = "viewMoviesModule";

angular.module(name, [])
    .service('movieService', movieService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("viewMovies", viewMoviesComponent)
    .component("deleteMovie", deleteMovieComponent)