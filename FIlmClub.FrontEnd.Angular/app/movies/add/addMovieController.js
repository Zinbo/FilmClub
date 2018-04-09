export default class AddMovieController {
    constructor($scope, addMovieService, $rootScope) {
        $scope.addMovie = this.addMovie;
        this.$scope = $scope;
        this.addMovieService = addMovieService;
        this.$rootScope = $rootScope;
    }

    addMovie() {
        const $scope = this.$scope;
        if ($scope.addMovieForm.$invalid) $scope.addMovieForm.submitted=true;

        const imdbLink = $scope.imdbLink;
        if(imdbLink) this.addMovieService.addMovie(imdbLink)
        .then((response) => {
            this.$rootScope.$broadcast('alert', {status: 'success', message: 'Movie Added!'});
        })
        .catch((response) => {
            this.$rootScope.$broadcast('alert', {status: 'danger', message: `Server responded with ${response.data.message}`});
        });
    }    
}

AddMovieController.$inject = ['$scope', 'addMovieService', '$rootScope'];

