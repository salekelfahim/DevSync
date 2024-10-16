<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
  <title>DevSync - Requests</title>
</head>
<body class="bg-gray-900 text-white">
<section class="container px-6 mx-auto py-10">
  <h2 class="text-lg font-medium text-gray-200 dark:text-white">List of Requests</h2>
  <div class="flex flex-col mt-6">
    <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
      <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
        <div class="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
          <div>
            <a href="users?action=list" class="text-white bg-green-700 hover:bg-green-800 focus:ring-4 focus:ring-green-300 font-medium rounded-lg text-sm px-5 py-2.5 dark:bg-green-600 dark:hover:bg-green-700 focus:outline-none dark:focus:ring-green-800">Home</a>
          </div>
            <table class="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
            <thead class="bg-gray-800">
            <tr>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">User</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Task</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Status</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Created At</th>
              <th scope="col" class="relative py-3.5 px-4">Actions</th>
            </tr>
            </thead>
            <tbody class="bg-gray-900 divide-y divide-gray-700">
            <c:forEach var="request" items="${requests}">
              <tr>
                <td class="px-4 py-4 text-sm font-medium text-gray-200 whitespace-nowrap">${request.user.firstName} ${request.user.lastName}</td>
                <td class="px-4 py-4 text-sm font-normal text-gray-200 whitespace-nowrap">${request.task.title}</td>
                <td class="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">${request.status}</td>
                <td class="px-4 py-4 text-sm text-gray-500 dark:text-gray-300 whitespace-nowrap">${request.createdAt}</td>
                <td class="px-4 py-4 text-sm whitespace-nowrap">
                  <div class="flex gap-2">
                    <c:if test="${request.status == 'PENDING'}">
                      <form action="${pageContext.request.contextPath}/requests" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="approve">
                        <input type="hidden" name="requestId" value="${request.id}">
                        <button type="submit" class="px-4 py-2 bg-green-500 border rounded-lg text-sm font-medium text-gray-200 transition-colors duration-200 hover:bg-green-600 dark:hover:bg-gray-800">
                          Approve
                        </button>
                      </form>
                      <form action="requests" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="reject">
                        <input type="hidden" name="requestId" value="${request.id}">
                        <button type="submit" class="px-4 py-2 bg-red-500 border rounded-lg text-sm font-medium text-gray-200 transition-colors duration-200 hover:bg-red-600 dark:hover:bg-gray-800">
                          Reject
                        </button>
                      </form>
                    </c:if>
                  </div>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>