/**
 * Created by Brandon on 5/2/2017.
 */
angular.module('Docstar.Users').controller('Docstar.Documents.EditUserController', function( $scope, $uibModalInstance, user ) {
    $scope.user = user;
    $scope.updateUser = {
    	id: user.id,
    	email: user.email,
    	firstName: user.firstName,
    	lastName: user.lastName,
    	username: user.username,
    	password : '',
        roles: user.roles,
        isEnabled: user.isEnabled	
    };

    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    };

    $scope.update = function() {
        $uibModalInstance.close( $scope.updateUser );
    };


});
