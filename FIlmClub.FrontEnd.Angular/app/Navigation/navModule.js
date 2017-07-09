import angular from "angular";
import navComponent from './navComponent';

export default name = "navModule";

angular.module(name, [])
    .component("navigation", navComponent);