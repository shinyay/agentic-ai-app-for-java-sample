package com.vibecode.studio.service.agent;

import org.springframework.stereotype.Service;

@Service
public class MockChatClient implements ChatClient {
    
    @Override
    public ChatResponse call(String prompt) {
        String response = generateMockResponse(prompt);
        return new ChatResponse(
            new ChatResponse.ChatResult(
                new ChatResponse.ChatOutput(response)
            )
        );
    }
    
    private String generateMockResponse(String prompt) {
        String lowerPrompt = prompt.toLowerCase();
        
        if (lowerPrompt.contains("extract") && lowerPrompt.contains("project")) {
            return """
                NAME: Todo App with Real-time Updates
                DESCRIPTION: A modern todo application with real-time collaboration features, user authentication, and responsive design. Users can create, edit, and delete tasks with real-time synchronization across devices.
                TECH: Spring Boot, Java 17, WebSocket, React, PostgreSQL, Redis
                """;
        }
        
        if (lowerPrompt.contains("generate code") || lowerPrompt.contains("code for")) {
            return """
                Here's a Java Spring Boot controller for your todo application:
                
                ```java
                @RestController
                @RequestMapping("/api/todos")
                public class TodoController {
                    
                    @Autowired
                    private TodoService todoService;
                    
                    @GetMapping
                    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam String userId) {
                        List<Todo> todos = todoService.getTodosByUser(userId);
                        return ResponseEntity.ok(todos);
                    }
                    
                    @PostMapping
                    public ResponseEntity<Todo> createTodo(@RequestBody CreateTodoRequest request) {
                        Todo todo = todoService.createTodo(request.getTitle(), request.getUserId());
                        return ResponseEntity.ok(todo);
                    }
                    
                    @PutMapping("/{id}")
                    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoRequest request) {
                        Todo todo = todoService.updateTodo(id, request.getTitle(), request.isCompleted());
                        return ResponseEntity.ok(todo);
                    }
                    
                    @DeleteMapping("/{id}")
                    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
                        todoService.deleteTodo(id);
                        return ResponseEntity.noContent().build();
                    }
                }
                ```
                
                This controller provides full CRUD operations for todos with proper REST endpoints.
                """;
        }
        
        if (lowerPrompt.contains("refactor") || lowerPrompt.contains("nestjs")) {
            return """
                I can help you refactor your backend to NestJS. Here's a basic structure:
                
                ```typescript
                @Controller('todos')
                export class TodosController {
                  constructor(private readonly todosService: TodosService) {}
                
                  @Get()
                  findAll(@Query('userId') userId: string) {
                    return this.todosService.findByUser(userId);
                  }
                
                  @Post()
                  create(@Body() createTodoDto: CreateTodoDto) {
                    return this.todosService.create(createTodoDto);
                  }
                
                  @Put(':id')
                  update(@Param('id') id: string, @Body() updateTodoDto: UpdateTodoDto) {
                    return this.todosService.update(+id, updateTodoDto);
                  }
                
                  @Delete(':id')
                  remove(@Param('id') id: string) {
                    return this.todosService.remove(+id);
                  }
                }
                ```
                
                This provides the same functionality with NestJS decorators and dependency injection.
                """;
        }
        
        // Default conversational response
        return """
            I'm VibeCode Studio AI, ready to help you build amazing software! I can help you with:
            
            - Creating new projects from natural language descriptions
            - Generating code snippets and complete components
            - Refactoring existing code
            - Adding tests and documentation
            - Suggesting best practices and security improvements
            
            What would you like to work on today?
            """;
    }
}