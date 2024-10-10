<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DevSync - Tasks</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
</head>
<body class="bg-gray-900 text-white flex">

<section class="container p-6 mx-auto">
  <div class="sm:flex sm:items-center sm:justify-between">
    <h2 class="text-lg font-medium text-gray-200">Tasks</h2>
    <div class="mt-4">
      <a href="tasks?action=create" class="flex items-center justify-center px-5 py-2 text-sm tracking-wide text-white transition-colors duration-200 bg-blue-500 rounded-lg gap-x-2 hover:bg-blue-600">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-5 h-5">
          <path stroke-linecap="round" stroke-linejoin="round" d="M12 9v6m3-3H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
        <span>Add Task</span>
      </a>
    </div>
  </div>

  <div class="flex flex-col mt-6">
    <div class="-mx-4 -my-2 overflow-x-auto sm:-mx-6 lg:-mx-8">
      <div class="inline-block min-w-full py-2 align-middle md:px-6 lg:px-8">
        <div class="overflow-hidden border border-gray-200 dark:border-gray-700 md:rounded-lg">
          <table class="min-w-full divide-y divide-gray-200 dark:divide-gray-700">
            <thead class="bg-gray-800">
            <tr>
              <th scope="col" class="py-3.5 px-4 text-sm font-normal text-left text-gray-400 dark:text-gray-400">N°</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Title</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Status</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Assignee</th>
              <th scope="col" class="px-4 py-3.5 text-sm font-normal text-left text-gray-400 dark:text-gray-400">Action</th>
            </tr>
            </thead>
            <tbody class="bg-gray-900 divide-y divide-gray-700">
            <c:forEach var="task" items="${tasks}">
              <tr>
                <td class="px-4 py-4 text-sm font-medium text-gray-200 whitespace-nowrap">#${task.id}</td>
                <td class="px-4 py-4 text-sm text-gray-300 whitespace-nowrap">${task.title}</td>
                <td class="px-4 py-4 text-sm whitespace-nowrap">
                                <span class="inline-flex items-center px-3 py-1 rounded-full
                                    <c:choose>
                                        <c:when test="${task.status == 'NOT_STARTED'}">bg-yellow-100 text-yellow-500</c:when>
                                        <c:when test="${task.status == 'IN_PROGRESS'}">bg-blue-100 text-blue-500</c:when>
                                        <c:when test="${task.status == 'COMPLETED'}">bg-green-100 text-green-500</c:when>
                                        <c:when test="${task.status == 'CANCELED'}">bg-red-100 text-red-500</c:when>
                                    </c:choose>">
                                    <h2 class="text-xs font-normal">${task.status}</h2>
                                </span>
                </td>
                <td class="px-4 py-4 text-sm text-gray-300 whitespace-nowrap">
                  <c:choose>
                    <c:when test="${not empty task.assignee}">
                      <div class="flex items-center gap-x-2">
                        <img class="object-cover w-8 h-8 rounded-full" src="https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixlib=rb-1.2.1&auto=format&fit=crop&w=880&q=80" alt="">
                        <h2 class="text-sm font-medium text-gray-200">${task.assignee.firstName} ${task.assignee.lastName}</h2>
                      </div>
                    </c:when>
                    <c:otherwise>
                      <h2 class="text-xs font-medium text-red-400">Not Assigned</h2>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td class="px-4 py-4 text-sm whitespace-nowrap">
                  <button class="text-gray-500 transition-colors duration-200 hover:text-indigo-500 focus:outline-none">
                    <span class="material-symbols-outlined">visibility</span>
                  </button>
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

</body>
</html>