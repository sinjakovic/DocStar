/**
 * Created by Brandon on 4/21/2017.
 */
angular.module('Docstar.Users').controller('Docstar.Documents.NewUserController', function( $scope, $uibModalInstance ) {
    $scope.newUser = {
        firstName : '',
        lastName : '',
        email : '',
        password : '',
        username : '',
        phoneNumber : '',
        roles: [{name:"ROLE_USER"}],
        isEnabled: true,
        isAccountNonLocked: true,
        isAccountNonExpired: true,
        isCredentialsNonExpired: true
    }

    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    }

    $scope.create = function() {
        $uibModalInstance.close( $scope.newUser );
    }

});
