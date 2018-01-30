/**
 * Created by Brandon on 4/21/2017.
 */
users = angular.module('Docstar.Users', ['ngRoute', 'ngResource', 'Session']);

users.config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/admin/users', {
        templateUrl: 'resources/views/admin/users/users.html',
        controller: 'Docstar.Users.Controller'
    });
}]);

users.controller('Docstar.Users.Controller', ['$scope', '$resource', 'Session.Service', '$uibModal', '$routeParams', function($scope, $resource, Session, $modal, $routeParams) {
    var Users = $resource('api/v1/admin/users/:uid',{ }, { 'update' : { method : 'PUT' } });
    $scope.users = Users.query( );

    $scope.hasRole = function( user, role ) {
        for( var i = 0; i < user.roles.length; i++) {
            if( user.roles[i].name === role ) return true;
        }
        return false;
    };

    $scope.enabled = function( user ) {
        return user.isEnabled;
    };

    $scope.newUser = function () {
        var modal = $modal.open({
                templateUrl: 'resources/views/admin/users/newUser.html',
                controller: 'Docstar.Documents.NewUserController',

            }
        );

        modal.result.then(function (newUser) {
            Users.save(
                newUser,
                function (savedRating) {
                    $scope.users = Users.query( );
                });
        });
    };

    $scope.editUser = function(user){
        var modal = $modal.open({
                templateUrl: 'resources/views/admin/users/editUser.html',
                controller: 'Docstar.Documents.EditUserController',
                resolve : {
                    user : user
                }
            }
        );

        modal.result.then(function (newUser) {
        	if(newUser.password === ''){
        		delete newUser.password;
        	}
            Users.update({uid: newUser.id},
                 newUser,
                 function (savedRating) {
                     $scope.users = Users.query( );
             });
        });
    };
}]);