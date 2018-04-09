export default class AddMovieService {
    constructor($http, getBackEndServiceUrl) {
        this.$http = $http;
        this.getBackEndServiceUrl = getBackEndServiceUrl;
    }

    addMovie(imdbLink) {
        if(!imdbLink) return;
        return this.$http.post(`${this.getBackEndServiceUrl.query()}/movies`, {externalId: imdbLink });
    }
}

AddMovieService.$inject = ['$http', 'getBackEndServiceUrl']