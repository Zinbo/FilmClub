import angular from "angular";
import navController from "./navController";
import navDirective from "./navDirective";

export default name = "navModule";

angular.module(name, [])
    .controller("navController", ['$scope', navController])
    .directive("navigation", navDirective);