import deleteController from './deleteController';

export const deleteComponent = {
    templateUrl: './movies/delete/delete.html',
    controller: deleteController,
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
};

export default deleteComponent;
