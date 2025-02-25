import type { NextConfig } from 'next';

const nextConfig: NextConfig = {
  postcss: {
    plugins: {
      'postcss-pxtorem': {
        rootValue: 16,
        propList: ['*']
      },
      autoprefixer: {}
    },
    sassOptions: {
      quietDeps: true
    }
  }
};

export default nextConfig;
