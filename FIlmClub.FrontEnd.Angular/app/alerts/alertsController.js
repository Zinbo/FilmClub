export default class AlertsController {
    constructor($rootScope) {
        this.alerts = [];
        $rootScope.$on('alert', (event, args) => {
            this.alerts.push(args);
        });
    }
}

AlertsController.$inject = ['$rootScope'];