import angular from "angular";
import navigationModule from './Navigation/navModule';

const name = "app";

console.log("In Module")

angular.module(name, [navigationModule])
    .controller('GreetingController', ['$scope', function ($scope) {
        $scope.greeting = 'Hola!';
        console.log("hola!");
    }])
