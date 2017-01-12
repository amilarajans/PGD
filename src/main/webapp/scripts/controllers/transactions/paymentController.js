/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('paymentController', ['$rootScope', '$scope', '$http', 'toastr', '$uibModal', '$uibModalInstance', 'element','selectedStudent',
    function ($rootScope, $scope, $http, toastr, $uibModal, $uibModalInstance, element,selectedStudent) {

        $scope.element = element;
        $scope.selectedStudent = selectedStudent;
    }]);