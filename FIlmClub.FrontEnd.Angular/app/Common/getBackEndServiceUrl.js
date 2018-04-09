export default class GetBackEndServiceUrl {
    constructor($location) {
        const host = $location.host();
        this.backEndServiceUrl = "";
        if(host.includes("localhost")) this.backEndServiceUrl = "http://localhost:8080";
    }

    query(){
        return this.backEndServiceUrl;
    }
}

GetBackEndServiceUrl.$inject = ["$location"];