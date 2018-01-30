/**
 * Created by Brandon on 4/30/2017.
 */
angular.module('Docstar.Admin.Lists').controller('Docstar.Lists.EditListUsersController', function( $scope, $uibModalInstance, userList, selectedUsers ) {

    $scope.users = userList;

    $scope.list = {
    updatedList: selectedUsers
    };
    $scope.cancel = function() {
        $uibModalInstance.dismiss();
    };

    $scope.apply = function() {
        $uibModalInstance.close( $scope.list.updatedList );
    };

//    $scope.inList = function(userName) {
//        for(var i = 0; i < selectedUsers.length; i++){
//            if(selectedUsers[i].username === userName){
//                return true;
//            }
//        }
//    }
});