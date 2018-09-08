export default class MovieService {
    constructor($http, getBackEndServiceUrl) {
        this.$http = $http;
        this.getBackEndServiceUrl = getBackEndServiceUrl;
    }

    addMovie(movieDbId) {
        if(!movieDbId) return;
        return this.$http.post(`${this.getBackEndServiceUrl.query()}/movies`, {externalId: movieDbId });
    }
}

MovieService.$inject = ['$http', 'getBackEndServiceUrl']