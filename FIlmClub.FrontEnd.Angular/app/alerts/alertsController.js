import toastr from 'toastr';
export default class AlertsController {
    constructor($rootScope) {
        this.alerts = [];
        $rootScope.$on('alert', (event, args) => {
            if(args.status === 'success') toastr.success(args.message);
            else if(args.status === 'danger') toastr.error(args.message, 'Something went wrong!');
        });
    }
}

AlertsController.$inject = ['$rootScope'];