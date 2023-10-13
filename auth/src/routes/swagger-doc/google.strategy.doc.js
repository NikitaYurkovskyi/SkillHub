/**
 * @swagger
 * tags:
 *   name: Google strategy
 *   description: Google strategy implementation
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
 * /auth/google:
 *   get:
 *     summary: Initiate Google authentication
 *     tags: [Google strategy]
 *     responses:
 *       302:
 *         description: Redirect to Google authentication page
 *         headers:
 *           Location:
 *             schema:
 *               type: string
 *               description: URL to Google authentication page
 */

/**
 * @swagger
 * /auth/google/callback:
 *   get:
 *     summary: Callback after successful Google authentication
 *     tags: [Google strategy]
 *     parameters:
 *       - in: query
 *         name: code
 *         required: true
 *         schema:
 *           type: string
 *         description: Authorization code from Google
 *     responses:
 *       200:
 *         description: Successful Google authentication
 *         content:
 *           application/json:
 *             schema:
 *               $ref: '#/components/schemas/AuthResponse'
 *       302:
 *         description: Redirect to the home page in case of authentication failure
 */
