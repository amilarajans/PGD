/**
 * Created by Manoj Janaka on 14-11-2016.
 */
activitiAdminApp.controller('paymentController', ['$rootScope', '$filter', '$scope', '$http', 'toastr', '$uibModal', '$uibModalInstance', 'element','selectedStudent',
    function ($rootScope, $filter, $scope, $http, toastr, $uibModal, $uibModalInstance, element,selectedStudent) {

        $scope.element = element;
        $scope.selectedStudent = selectedStudent;
        $scope.paymentObj = {};
        $scope.paymentObj.paymentDate = $filter("date")(Date.now(), 'yyyy-MM-dd');

        $scope.cancel = function () {
            $uibModalInstance.dismiss('cancel');
        };
    }]);