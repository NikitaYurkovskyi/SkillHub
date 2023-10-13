// swaggerDefinitions.js
/**
 * @swagger
 * tags:
 *   name: Local strategy
 *   description: Local strategy implementation
 */

/**
 * @swagger
 * components:
 *   schemas:
 *     UserDto:
 *       type: object
 *       description: Used as dto for signup
 *       properties:
 *         email:
 *           type: string
 *           format: email
 *           description: User's email address
 *         password:
 *           type: string
 *           description: User's password
 *         displayName:
 *           type: string
 *           description: User's display name
 *
 *
 *     User:
 *       type: object
 *       description: Used as dto for responce or for tokens payload
 *       properties:
 *         id:
 *           type: string
 *           format: id
 *           description: User id
 *         email:
 *           type: string
 *           format: email
 *           description: User's email address
 *         displayName:
 *           type: string
 *           description: User's display name
 *         providerName:
 *           type: string
 *           description: Provider name [local, google, github]
 *         providerId:
 *           type: string
 *           description: Provider id (if provider is local equal to zero)
 *
 *
 *       required:
 *         - email
 *         - password
 *         - displayName
 *     AuthResponse:
 *       type: object
 *       properties:
 *         accessToken:
 *           type: string
 *           description: Access token for authentication
 *         refreshToken:
 *           type: string
 *           description: Refresh token for authentication
 *         userData:
 *           $ref: '#/components/schemas/UserDto'
 */

/**
 * @swagger
 * /auth/signup:
 *   post:
 *     summary: User registration
 *     tags: [Local strategy]
 *     requestBody:
 *       description: User registration data
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UserDto'
 *     responses:
 *       200:
 *         description: Successful registration
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/AuthResponse'
 */

/**
 * @swagger
 * /auth/signin:
 *   post:
 *     summary: User login
 *     tags: [Local strategy]
 *     requestBody:
 *       description: User login data
 *       required: true
 *       content:
 *         application/json:
 *           schema:
 *             $ref: '#/components/schemas/UserDto'
 *     responses:
 *       200:
 *         description: Successful login
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/AuthResponse'
 *     cookie:
 *       refreshToken:
 *         type: string
 *         description: Refresh token cookie for authentication
 */

// Можете добавить другие определения Swagger здесь
