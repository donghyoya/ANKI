import Link from 'next/link';

export default function AuthTestPage() {
  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100 p-4">
      <div className="bg-white p-8 rounded-lg shadow-md w-full max-w-md text-center">
        <h1 className="text-2xl font-bold mb-6 text-gray-800">Auth Test</h1>
        <ul className="space-y-4">
          <li>
            <Link
              href="/auth/test/client"
              className="block bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 px-4 rounded transition"
            >
              Client Side
            </Link>
          </li>
          <li>
            <Link
              href="/auth/test/server"
              className="block bg-green-500 hover:bg-green-600 text-white font-semibold py-2 px-4 rounded transition"
            >
              Server Side
            </Link>
          </li>
        </ul>
      </div>
    </div>
  );
}
