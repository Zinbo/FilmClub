export default class ViewMoviesController {
    constructor($scope, movieService, $rootScope, $uibModal) {
        $scope.getMovies = this.getMovies;
        this.$scope = $scope;
        this.movieService = movieService;
        this.$uibModal = $uibModal;
        this.$rootScope = $rootScope;
        this.getMovies();
        this.baseImageUri = 'https://image.tmdb.org/t/p/w342';
        $rootScope.$on('changeMovies', () => {
            this.getMovies();
        });
    }

    getMovies() {
        const $scope = this.$scope;
        this.movieService.getMovies().then(data => {
            const movies = data;
            if(!movies || movies.length === 0) return;

            const noOfRows = Math.ceil(movies.length / 3);
            const noOfItemsPerRow = 3;
            const moviesGroupedByRow = [];
            for (let rowIndex = 0; rowIndex < noOfRows; rowIndex++) {
                moviesGroupedByRow[rowIndex] = [];
            }
            for (let rowIndex = 0; rowIndex < noOfRows; rowIndex++) {
                for (let columnIndex = 0; columnIndex < noOfItemsPerRow; columnIndex++) {
                    const movieIndex = noOfItemsPerRow*rowIndex + columnIndex
                    const movie = movies[movieIndex];
                    if(!movie) break; // must have had an odd number of movies, need to break out as we've processed all of the movies
                    movie.imageLink = movie.imageLink ? `${this.baseImageUri}${movie.imageLink}` : 'shared/images/default_poster.jpg';
                    moviesGroupedByRow[rowIndex][columnIndex] = movie;
                }
            }
            $scope.moviesGroupedByRow = moviesGroupedByRow;
        }).catch((e) => {
            console.log(e);
            this.$rootScope.$broadcast('alert', {status: 'danger', message: `Cannot get movies!`});
        });
    }

    openDeleteModal(movie) {
        if(!movie || !movie.id) return;
        this.$uibModal.open({
          animation: true,
          component: 'deleteMovie',
          resolve: {
            movie: () => {
              return movie;
            }
          }
        });
    };
}

ViewMoviesController.$inject = ['$scope', 'movieService', '$rootScope', '$uibModal'];

