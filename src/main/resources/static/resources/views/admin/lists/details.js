/**
 * Created by Brandon on 4/29/2017.
 */
angular.module('Docstar.Admin.Lists').controller('Docstar.Admin.Lists.Details.Controller',
    ['$scope', '$resource', '$routeParams', '$uibModal', 'Session.Service', function ($scope, $resource, $routeParams, $modal, Session) {
        var List = $resource('api/v1/admin/lists/:lid', {}, { 'update' : { method : 'PUT'} } );
        var Users = $resource('api/v1/admin/users', {});
        $scope.users = Users.query();

        $scope.list = List.get({lid: $routeParams.lid});
        
        $scope.editUsers = function () {
            var modal = $modal.open({
                    templateUrl: 'resources/views/admin/lists/editListUsers.html',
                    controller: 'Docstar.Lists.EditListUsersController',
                    resolve: {
                        userList: function () {
                            return $scope.users
                        },
                        selectedUsers: function(){
                            return $scope.list.users
                        }

                    }
                }
            );

            modal.result.then(function (users) {
                $scope.list.users = users;
                List.update({lid: $scope.list.id},
                    $scope.list,
                    function (updatedList) {
                        $scope.list = updatedList;
                    });
            });
        }

        $scope.editDocs = function () {
            var modal = $modal.open({
                    templateUrl: 'resources/views/admin/lists/editListDocuments.html',
                    controller: 'Docstar.Lists.EditListDocumentsController',
                     resolve: {
                         selectedDocs: function () {
                             return $scope.list.docIds
                         }
                     }
                }
            );

            modal.result.then(function (docIds) {
                $scope.list.docIds = docIds;
                List.update({lid: $scope.list.id},
                    $scope.list,
                    function (updatedList) {
                        $scope.list = updatedList;
                    });
            });
        }
    }]
);