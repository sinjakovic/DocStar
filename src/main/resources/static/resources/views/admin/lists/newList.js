/**
 * Created by Brandon on 4/30/2017.
 */
angular.module('Docstar.Admin.Lists').controller('Docstar.Lists.NewListController', function( $scope, $uibModalInstance ) {
    $scope.newList = {
        name : ''
    }

    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    }

    $scope.apply = function() {
        $uibModalInstance.close( $scope.newList );
    }

});
