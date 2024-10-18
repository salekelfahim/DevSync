<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Tasks</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body class="bg-gray-50 dark:bg-gray-900">
<section class="flex justify-center items-center min-h-screen px-4">
  <div class="w-full max-w-3xl p-8 bg-white rounded-lg shadow dark:bg-gray-800 lg:px-12">
    <h1 class="text-3xl font-semibold text-gray-800 capitalize dark:text-white mb-6">Hello, ${loggedUser.firstName}!</h1>
    <div class="flex justify-between items-center mb-6">
      <a href="tasks?action=create" class="px-4 py-2 text-white bg-blue-600 rounded hover:bg-blue-700">
        Add New Task
      </a>
    </div>
    <div class="grid grid-cols-1 gap-6">
      <div>
        <h2 class="text-2xl font-semibold text-gray-800 dark:text-white">Not Started</h2>
        <div class="mt-4">
          <c:forEach var="task" items="${notStartedTasks}">
            <div class="p-4 mb-4 bg-gray-100 rounded-lg dark:bg-gray-700">
              <h3 class="font-medium text-gray-800 dark:text-gray-200">${task.title}</h3>
              <p class="text-gray-600 dark:text-gray-400">${task.description}</p>
              <p class="mt-2 text-sm text-gray-500 dark:text-gray-400">Due: ${task.dueDate}</p>
              <form action="${pageContext.request.contextPath}/tasks" method="get" class="mt-2">
                <input type="hidden" name="action" value="changeStatus">
                <input type="hidden" name="taskId" value="${task.id}">
                <input type="hidden" name="newStatus" value="IN_PROGRESS">
                <button type="submit" class="px-4 py-2 text-white bg-blue-600 rounded hover:bg-blue-700">
                  Start Task
                </button>
              </form>
              <c:if test="${!task.refused}">
                <form action="${pageContext.request.contextPath}/tasks" method="post" class="mt-2"
                      onsubmit="return confirm('Are you sure you want to refuse this task? This will consume one of your refuse tokens.');">
                  <input type="hidden" name="action" value="refuseTask">
                  <input type="hidden" name="taskId" value="${task.id}">
                  <button type="submit" class="px-4 py-2 text-white bg-red-600 rounded hover:bg-red-700">
                    Refuse Task
                  </button>
                </form>
              </c:if>
            </div>
          </c:forEach>
        </div>
      </div>
      <div>
        <h2 class="text-2xl font-semibold text-gray-800 dark:text-white">In Progress</h2>
        <div class="mt-4">
          <c:forEach var="task" items="${inProgressTasks}">
            <div class="p-4 mb-4 bg-gray-100 rounded-lg dark:bg-gray-700">
              <h3 class="font-medium text-gray-800 dark:text-gray-200">${task.title}</h3>
              <p class="text-gray-600 dark:text-gray-400">${task.description}</p>
              <p class="mt-2 text-sm text-gray-500 dark:text-gray-400">Due: ${task.dueDate}</p>
              <form action="${pageContext.request.contextPath}/tasks" method="get" class="mt-2">
                <input type="hidden" name="action" value="changeStatus">
                <input type="hidden" name="taskId" value="${task.id}">
                <input type="hidden" name="newStatus" value="COMPLETED">
                <button type="submit" class="px-4 py-2 text-white bg-green-600 rounded hover:bg-green-700">
                  Complete Task
                </button>
              </form>
            </div>
          </c:forEach>
        </div>
      </div>
      <div>
        <h2 class="text-2xl font-semibold text-gray-800 dark:text-white">Completed</h2>
        <div class="mt-4">
          <c:forEach var="task" items="${completedTasks}">
            <div class="p-4 mb-4 bg-gray-100 rounded-lg dark:bg-gray-700">
              <h3 class="font-medium text-gray-800 dark:text-gray-200">${task.title}</h3>
              <p class="text-gray-600 dark:text-gray-400">${task.description}</p>
              <p class="mt-2 text-sm text-gray-500 dark:text-gray-400">Due: ${task.dueDate}</p>
            </div>
          </c:forEach>
        </div>
      </div>
      <div>
        <h2 class="text-2xl font-semibold text-gray-800 dark:text-white">Canceled</h2>
        <div class="mt-4">
          <c:forEach var="task" items="${canceledTasks}">
            <div class="p-4 mb-4 bg-gray-100 rounded-lg dark:bg-gray-700">
              <h3 class="font-medium text-gray-800 dark:text-gray-200">${task.title}</h3>
              <p class="text-gray-600 dark:text-gray-400">${task.description}</p>
              <p class="mt-2 text-sm text-gray-500 dark:text-gray-400">Due: ${task.dueDate}</p>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
    <div class="mt-8">
      <form action="${pageContext.request.contextPath}/users?action=logout" method="get">
        <button type="submit" class="px-4 py-2 text-white bg-red-600 rounded hover:bg-red-700">
          Logout
        </button>
      </form>
    </div>
  </div>
</section>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>