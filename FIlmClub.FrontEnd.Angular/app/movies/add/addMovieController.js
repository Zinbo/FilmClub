export default class AddMovieController {
    constructor($scope, addMovieService) {
        $scope.addMovie = this.addMovie;
        this.$scope = $scope;
        this.addMovieService = addMovieService;
    }

    addMovie() {
        if (this.$scope.addMovieForm.$invalid) this.$scope.addMovieForm.submitted=true;

        const imdbLink = this.$scope.imdbLink;
        if(imdbLink) this.addMovieService.addMovie(imdbLink)
        .then((response) => {
            console.log("success!");
        })
        .catch((response) => {
            console.log("Failure...");
        });
    }    
}

AddMovieController.$inject = ['$scope', 'addMovieService'];

