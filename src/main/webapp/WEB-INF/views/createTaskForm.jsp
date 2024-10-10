<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>DevSync - Tasks</title>
  <script src="${pageContext.request.contextPath}/assets/js/TaskValidation.js" type="text/javascript" defer></script>
  <script src="https://cdn.tailwindcss.com"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
  <link href="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.css" rel="stylesheet" />
</head>
<body class="bg-gray-50 dark:bg-gray-900">
<section class="flex justify-center items-center min-h-screen px-4">
  <div class="w-full max-w-3xl p-8 bg-white rounded-lg shadow dark:bg-gray-800 lg:px-12">
    <h2 class="text-3xl font-semibold text-gray-800 capitalize dark:text-white mb-6">Create New Task</h2>
    <form class="grid grid-cols-1 gap-6" action="tasks?action=create" method="post">
      <div>
        <label for="title" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Title</label>
        <input
                class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Title"
                type="text"
                name="title"
                id="title"
                required
        />
      </div>

      <div class="grid grid-cols-1 gap-4 sm:grid-cols-2">
        <div>
          <label for="creationDate" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Start Date</label>
          <input
                  class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                  type="date"
                  name="creationDate"
                  id="creationDate"
                  required
          />
        </div>

        <div>
          <label for="dueDate" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">End Date</label>
          <input
                  class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                  type="date"
                  name="dueDate"
                  id="dueDate"
                  required
          />
        </div>
      </div>

      <div>
        <label for="assignee_id" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Assignee</label>
        <select
                name="assignee_id"
                id="assignee_id"
                class="js-example-basic-single block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                required
        >
          <option value="" selected disabled>Assign to...</option>
          <c:forEach var="user" items="${users}">
            <option value="${user.id}">${user.firstName} ${user.lastName}</option>
          </c:forEach>
        </select>
      </div>

      <div>
        <label for="tags" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Tags</label>
        <select id="tags" class="js-example-basic-multiple block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500" name="tags[]" multiple="multiple">
          <c:forEach var="tag" items="${tags}">
            <option value="${tag.id}">${tag.name}</option>
          </c:forEach>
        </select>
      </div>

      <div>
        <label for="description" class="block mb-2 text-sm font-medium text-gray-700 dark:text-gray-200">Description</label>
        <textarea
                class="block w-full px-4 py-2 mt-2 text-gray-800 bg-gray-100 border border-gray-300 rounded-lg dark:bg-gray-700 dark:border-gray-600 dark:text-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                placeholder="Description"
                rows="8"
                name="description"
                id="description"
                required
        ></textarea>
      </div>

      <div class="mt-4">
        <button
                type="submit"
                class="flex items-center justify-center w-full px-6 py-3 mt-4 text-white bg-blue-600 rounded-lg hover:bg-blue-500 focus:outline-none focus:ring focus:ring-blue-300 focus:ring-opacity-50"
        >
          <span>Create Task</span>
        </button>
      </div>
    </form>
    <a href="tasks" class="mt-4 block text-center text-blue-600 hover:underline">Back to Tasks</a>
  </div>
</section>

<script>
  $(document).ready(function() {
    $('.js-example-basic-single').select2();
    $('.js-example-basic-multiple').select2();
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>