// swaggerDefinitions.js (добавьте в свой файл определений Swagger)

/**
 * @swagger
 * tags:
 *   name: Refresh
 *   description: Route for refreshing tokens
 */

/**
 * @swagger
 * components:
 *   schemas:
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
 *           $ref: '#/components/schemas/User'
 */

/**
 * @swagger
 * /auth/refresh:
 *   post:
 *     summary: Refresh access token
 *     tags: [Refresh]
 *     responses:
 *       200:
 *         description: Successful token refresh
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/AuthResponse'
 *       401:
 *         description: Unauthorized - Missing or invalid refresh token
 *       403:
 *         description: Forbidden - Invalid refresh token
 *     security:
 *       - cookieAuth: []
 *     parameters:
 *       - in: cookie
 *         name: refreshToken
 *         required: true
 *         schema:
 *           type: string
 *         description: Refresh token for authentication
 *     requestBody:
 *       description: Empty request body as the refresh token is passed in the cookie
 *       required: false
 *       content:
 *         application/json:
 *           schema:
 *             type: object
 *             properties:
 *               refreshToken:
 *                 type: string
 *                 description: Refresh token for authentication
 *             required:
 *               - refreshToken
 */

/**
 * @swagger
 * securitySchemes:
 *   cookieAuth:
 *     type: apiKey
 *     in: cookie
 *     name: refreshToken
 */

// Добавьте другие определения Swagger здесь
