export default class TheMovieDbService {
    constructor($http) {
        this.$http = $http;
    }

    getMovieResultsByName(searchTerm) {
        if(!searchTerm) return [];
        return this.$http.get(`https://api.themoviedb.org/3/search/movie`, {
            params: {
                query: searchTerm,
                api_key: 'c99917ae489bca2e3ad805c0ad92546b'
            }
        }).then(function(response){
            return response.data;
        });
    }
}

TheMovieDbService.$inject = ['$http']