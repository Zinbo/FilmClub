export default class ViewMoviesController {
    constructor($scope, movieService, $rootScope) {
        $scope.getMovies = this.getMovies;
        this.$scope = $scope;
        this.movieService = movieService;
        this.$rootScope = $rootScope;
        this.getMovies();
    }

    getMovies() {
        const $scope = this.$scope;
        this.movieService.getMovies().then(data => {
            $scope.movies = data;
        }).catch(() => {
            this.$rootScope.$broadcast('alert', {status: 'danger', message: `Cannot get movies!`});
        });
    }
}

ViewMoviesController.$inject = ['$scope', 'movieService', '$rootScope'];

