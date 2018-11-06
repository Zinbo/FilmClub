export default class MovieService {
    constructor($http, getBackEndServiceUrl) {
        this.$http = $http;
        this.getBackEndServiceUrl = getBackEndServiceUrl;
    }

    addMovie(movieDbId) {
        if(!movieDbId) return;
        return this.$http.post(`${this.getBackEndServiceUrl.query()}/movies`, {externalId: movieDbId });
    }

    getMovies() {
        return this.$http.get(`${this.getBackEndServiceUrl.query()}/movies`)
            .then(response => response.data);
    }

    deleteMovie(id) {
        return this.$http.delete(`${this.getBackEndServiceUrl.query()}/movies/${id}`);
    }

    vote(id, score) {
        return this.$http.post(`${this.getBackEndServiceUrl.query()}/movies/${id}/vote`, {score});
    }
}

MovieService.$inject = ['$http', 'getBackEndServiceUrl']