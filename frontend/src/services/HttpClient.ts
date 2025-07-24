import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios';

import { AuthInterceptor } from './AuthInterceptor';

export class HttpClient {
  private axiosInstance: AxiosInstance;

  constructor(
    private baseURL: string,
    private authInterceptor?: AuthInterceptor
  ) {
    this.axiosInstance = axios.create({
      baseURL,
      timeout: 5000,
      headers: {
        'Content-Type': 'application/json'
      }
    });

    this.setupInterceptors();
  }

  async get<T>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.axiosInstance.get<T>(url, config);
  }

  async post<T>(
    url: string,
    data?: unknown,
    config?: AxiosRequestConfig
  ): Promise<AxiosResponse<T>> {
    return this.axiosInstance.post<T>(url, data, config);
  }

  async put<T>(
    url: string,
    data?: unknown,
    config?: AxiosRequestConfig
  ): Promise<AxiosResponse<T>> {
    return this.axiosInstance.put<T>(url, data, config);
  }

  async delete<T>(url: string, config?: AxiosRequestConfig): Promise<AxiosResponse<T>> {
    return this.axiosInstance.delete<T>(url, config);
  }

  private setupInterceptors(): void {
    const interceptor = this.authInterceptor;
    if (!interceptor) return;

    this.axiosInstance.interceptors.request.use(
      (config) => interceptor.onRequest(config),
      (error) => interceptor.onError(error)
    );

    this.axiosInstance.interceptors.response.use(
      (response) => interceptor.onResponse(response),
      (error) => interceptor.onError(error)
    );
  }
}
