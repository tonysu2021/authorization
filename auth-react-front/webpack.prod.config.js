/*eslint no-undef: "error"*/
/*eslint-env node*/

const { CleanWebpackPlugin } = require('clean-webpack-plugin');
var HtmlWebpackPlugin = require('html-webpack-plugin');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const path = require('path');

module.exports = {
    mode: 'production',
    resolve: {
        extensions: ['.js', '.jsx'],
        alias: {
            '@src': path.resolve(__dirname, 'src/'),
            '@view': path.resolve(__dirname, 'src/view/'),
            '@store': path.resolve(__dirname, 'src/store/'),
            '@core': path.resolve(__dirname, 'src/core/'),
            '@assets': path.resolve(__dirname, 'src/assets/'),
            '@component': path.resolve(__dirname, 'src/core/component/'),
            '@material-asset': path.resolve(__dirname, 'src/assets/material/'),
        }
    },
    module: {
        rules: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader'
            },
            {
                test: /\.css$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    "css-loader",
                ]
            },
            {
                test: /\.styl$/,
                use: [
                    MiniCssExtractPlugin.loader,
                    'css-loader',
                    'stylus-loader'
                ]
            },
            {
                test: /\.(png|jpg|gif|jpeg|svg)$/,
                use: [
                    {
                        loader: 'url-loader',
                        options: {
                            limit: 1024,
                            name: 'assets/image/[name].[ext]',
                        }
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/index.html'
        }),
        new CleanWebpackPlugin(),
        new MiniCssExtractPlugin({
            filename: "[name].css",
            chunkFilename: "[id].css"
        })
    ],
    devServer: {
        historyApiFallback: true
    },
    externals: {
        // global app config object
        environments: JSON.stringify({
            apiHost: "http://127.0.0.1:10083",
            clientId: "admin",
            clientSecret: "123456",
            sseUrl: "",
            production: true,
            name: "prod",
            minutesToRefresh: 2,
        })
    }
}
