import type { NextConfig } from 'next';

const nextConfig: NextConfig = {
  postcss: {
    plugins: {
      'postcss-pxtorem': {
        rootValue: 16,
        propList: ['*']
      },
      autoprefixer: {}
    }
  }
};

export default nextConfig;
