/**
 * Created by Brandon on 4/21/2017.
 */
angular.module('Docstar.Users.Documents').controller('Docstar.Documents.NewRatingController', function( $scope, $uibModalInstance ) {
    $scope.newRating = {
        rating : ''
    }

    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    }

    $scope.rate = function() {
        $uibModalInstance.close( $scope.newRating );
    }

});
