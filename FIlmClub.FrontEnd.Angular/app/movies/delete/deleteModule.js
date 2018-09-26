import angular from "angular";
import deleteComponent from './deleteComponent';
import movieService from '../shared/movieService';
import getBackEndServiceUrl from '../../common/getBackEndServiceUrl';

export default name = "deleteModule";

angular.module(name, [])
    .service('movieService', movieService)
    .service('getBackEndServiceUrl', getBackEndServiceUrl)
    .component("deleteMovie", deleteComponent);