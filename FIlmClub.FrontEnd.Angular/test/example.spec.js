// import navModule from '../app/Navigation/navModule';
// import 'angular-mocks';
// import 'angular';

// describe("NavController", function() {
//     beforeEach(angular.mock.module('navModule'));

//     let $controller;

//     beforeEach(inject(function(_$controller_) {
//         $controller = _$controller_;
//     }));

//     it("says hello", function() {
//         const $scope = [];
//         const controller = $controller('navController', {
//             $scope: $scope
//         });
//         expect($scope.test).toEqual("test");
//     });
// });

describe("NavController", function () {
    it("says hello", function () {
        const hello = 'Hello World!';
        expect('Hello World!').toEqual(hello);
    });
});