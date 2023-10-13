/**
 * @swagger
 * tags:
 *   name: GitHub strategy
 *   description: Authentication operations
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
 * /auth/github:
 *   get:
 *     summary: Initiate GitHub authentication
 *     tags: [GitHub strategy]
 *     responses:
 *       302:
 *         description: Redirect to GitHub authentication page
 *         headers:
 *           Location:
 *             schema:
 *               type: string
 *               description: URL to GitHub authentication page
 */

/**
 * @swagger
 * /auth/github/callback:
 *   get:
 *     summary: Callback after successful GitHub authentication
 *     tags: [GitHub strategy]
 *     parameters:
 *       - in: query
 *         name: code
 *         required: true
 *         schema:
 *           type: string
 *         description: Authorization code from GitHub
 *     responses:
 *       200:
 *         description: Successful GitHub authentication
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/AuthResponse'
 *       302:
 *         description: Redirect to the home page in case of authentication failure
 */

// Добавьте другие определения Swagger здесь
