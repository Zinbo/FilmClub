export default class DeleteMovieController {
    
    constructor($rootScope, movieService) {
        this.$rootScope = $rootScope;
        this.movieService = movieService;
    }

    $onInit() {
        this.movie = this.resolve.movie;
    }

    ok() {
        this.movieService.deleteMovie(this.movie.id)
            .then(() => {
                this.$rootScope.$broadcast('alert', {status: 'success', message: `${this.movie.name} Deleted!`});
                this.$rootScope.$broadcast('changeMovies');
                this.close();
            })
            .catch((response) => {
                this.$rootScope.$broadcast('alert', {status: 'danger', message: `Server responded with ${response.status}`});
                this.close();
            });
    }

    cancel() {
        this.close();
    }
}

DeleteMovieController.$inject = ['$rootScope', 'movieService'];
