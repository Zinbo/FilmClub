export default class GetBackEndServiceUrl {
    constructor($location) {
        const host = $location.host();
        this.backEndServiceUrl = "";
        if(host.includes("localhost")) this.backEndServiceUrl = "http://localhost:9000";
    }

    query(){
        return this.backEndServiceUrl;
    }
}

GetBackEndServiceUrl.$inject = ["$location"];