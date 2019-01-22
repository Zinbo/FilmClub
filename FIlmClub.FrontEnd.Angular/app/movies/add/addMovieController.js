export default class AddMovieController {
    constructor($scope, movieService, theMovieDbService, $rootScope, $http) {
        $scope.addMovie = this.addMovie;
        this.$scope = $scope;
        this.movieService = movieService;
        this.$rootScope = $rootScope;
        this.$http = $http;
        this.theMovieDbService = theMovieDbService;

        this.modelOptions = {
            debounce: {
              default: 500,
              blur: 250
            },
            getterSetter: true
          };
    }

    addMovie() {
        const $scope = this.$scope;
        // Why do we set submitted to true if it's invalid?
        if ($scope.addMovieForm.$invalid) $scope.addMovieForm.submitted=true;
        const selectedMovie = $scope.selectedMovie;
        // Need to do something here to display an error
        if(!selectedMovie || !selectedMovie.id) {
            this.$rootScope.$broadcast('alert', {status: 'danger', message: `Please select a movie from the dropdown`});
            return;
        }
        if(selectedMovie.id) this.movieService.addMovie(selectedMovie.id)
            .then(() => {
                this.$rootScope.$broadcast('alert', {status: 'success', message: 'Movie Added!'});
                this.$rootScope.$broadcast('changeMovies');
            })
            .catch((response) => {
                this.$rootScope.$broadcast('alert', {status: 'danger', message: `Server responded with ${response.data.message}`});
            }
        );
    }

    getMovieResultsByName(searchTerm) {
        if(searchTerm.length < 3) return [];
        return this.theMovieDbService.getMovieResultsByName(searchTerm).then((movies) => {
            if(!movies || !movies.results) return [];
            return movies.results;
        });
    };
}

AddMovieController.$inject = ['$scope', 'movieService', 'theMovieDbService', '$rootScope', '$http'];

