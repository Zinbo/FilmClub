import angular from "angular";
import viewMoviesComponent from './viewMoviesComponent';
import movieService from '../shared/movieService';
import getBackEndServiceUrl from '../../common/getBackEndServiceUrl';
import uibootstrap from 'angular-ui-bootstrap';

export default name = "viewMoviesModule";

angular.module(name, [uibootstrap])
    .service('movieService', movieService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("viewMovies", viewMoviesComponent);