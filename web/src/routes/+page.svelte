<script>
    import { currentUser } from "$lib/stores/currentUser";
import { MoveUpRight } from "lucide-svelte";

</script>
<div class="flex flex-col w-full p-2 text-gray-200">
    <div class="flex max-md:flex-col-reverse items-center justify-between max-md:h-96 h-160 rounded-lg md:m-3 m-1">
        <div class="flex flex-col gap-3 items-center justify-center w-full md:w-1/3 h-full p-3 opacity-75 bg-base-100">
            <h1 class="text-3xl font-bona-nova">
                Tailoring
            </h1>
            <div class="prose prose-h1:text-lg prose-h2:text-md prose-p:sm font-bona-nova text-gray-200">
                Get your favorite design realised by our expert tailors
            </div>
            <a class="btn btn-primary max-md:w-full" href="/app/orders/new">Tailoring <MoveUpRight class="w-4 h-4"/> </a>
        </div>
        <div class="flex flex-col max-md:h-96 h-160 w-full md:w-2/3 gap-2 bg-cover bg-no-repeat bg-[url('/tailoring.jpg')] rounded-r-lg max-md:rounded-t-lg opacity-100">
        </div>
    </div>

    <div class="flex flex-row-reverse max-md:flex-col-reverse items-center justify-between max-md:h-96 h-160 rounded-lg md:m-3 m-1">
        <div class="flex flex-col gap-3 items-center justify-center w-full md:w-1/3 h-full p-3 opacity-75 bg-base-100">
            <h1 class="text-3xl font-bona-nova">
                Anyone can be a Tailor
            </h1>
            <div class="prose prose-h1:text-lg prose-h2:text-md prose-p:sm font-bona-nova text-gray-200">
                Become a tailor and earn money with us by sewing for others
            </div>
            <a class="btn btn-primary max-md:w-full" href="/app/{$currentUser.id}/apply">Apply <MoveUpRight class="w-4 h-4"/> </a>
        </div>
        <div class="flex flex-col max-md:h-96 h-160 w-full md:w-2/3 gap-2 bg-cover bg-no-repeat bg-[url('/becomeTailor.jpg')] rounded-l-lg max-md:rounded-t-lg opacity-100">
        </div>
    </div>

    <div class="flex max-md:flex-col-reverse items-center justify-between max-md:h-96 h-160 rounded-lg md:m-3 m-1">
        <div class="flex flex-col gap-3 items-center justify-center w-full md:w-1/3 h-full p-3 opacity-75 bg-base-100">
            <h1 class="text-3xl font-bona-nova">
                Tailors
            </h1>
            <div class="prose prose-h1:text-lg prose-h2:text-md prose-p:sm font-bona-nova text-gray-200">
                Find out about the top tailors working with us and their works
            </div>
            <a class="btn btn-primary max-md:w-full" href="/app/orders/new">Tailors <MoveUpRight class="w-4 h-4"/> </a>
        </div>
        <div class="flex flex-col max-md:h-96 h-160 w-full md:w-2/3 gap-2 bg-cover bg-no-repeat bg-[url('/tailor.jpg')] rounded-r-lg max-md:rounded-t-lg opacity-100">
        </div>
    </div>
</div>

<footer class="bg-gray-800 text-white py-8">
    <div class="container mx-auto px-4">
      <div class="flex flex-col md:flex-row justify-between items-start md:items-center">
        <div class="mb-6 md:mb-0">
          <h2 class="text-xl font-bold mb-2">ReTailor</h2>
          <p class="mb-1">Helpline: +880 9XXXXXXXXXX</p>
          <p>Address: 1234, Business Park, Dhaka, Bangladesh</p>
        </div>
        
        <div class="mb-6 md:mb-0">
          <h3 class="text-lg font-semibold mb-2">Developers</h3>
          <ul>
            <li class="mb-1">Mahmudul Hasan - Contact: 01XXXXXXXXX</li>
            <li class="mb-1">Ehsan Pahloan - Contact: 01XXXXXXXXX</li>
          </ul>
        </div>
        
        <div>
          <h3 class="text-lg font-semibold mb-2">Follow Us</h3>
          <div class="flex space-x-4">
            <!-- svelte-ignore a11y-invalid-attribute -->
            <a href="#" class="text-gray-400 hover:text-white">
              <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
                <path d="M22.46 6.03c-.77.34-1.61.58-2.48.68a4.33 4.33 0 0 0 1.89-2.4c-.84.5-1.77.85-2.76 1.04a4.28 4.28 0 0 0-7.28 3.9 12.15 12.15 0 0 1-8.84-4.49 4.24 4.24 0 0 0-.58 2.15 4.28 4.28 0 0 0 1.9 3.57 4.22 4.22 0 0 1-1.94-.54v.05a4.28 4.28 0 0 0 3.44 4.2c-.8.22-1.63.25-2.45.1a4.29 4.29 0 0 0 4 2.97A8.61 8.61 0 0 1 2 19.54 12.16 12.16 0 0 0 8.29 21c7.5 0 11.6-6.21 11.6-11.6v-.53A8.3 8.3 0 0 0 22.46 6z" />
              </svg>
            </a>
            <!-- svelte-ignore a11y-invalid-attribute -->
            <a href="#" class="text-gray-400 hover:text-white">
              <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12 2.04c-5.52 0-10 4.48-10 10 0 4.42 3.58 8.09 8.14 8.95v-6.33h-2.44V12h2.44V9.73c0-2.42 1.46-3.75 3.67-3.75 1.06 0 2.17.19 2.17.19v2.37h-1.22c-1.2 0-1.57.75-1.57 1.52V12h2.7l-.43 2.66h-2.27v6.33c4.56-.86 8.14-4.53 8.14-8.95 0-5.52-4.48-10-10-10z" />
              </svg>
            </a>
            <!-- svelte-ignore a11y-invalid-attribute -->
            <a href="#" class="text-gray-400 hover:text-white">
              <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 24 24">
                <path d="M9.04 9.04a2.25 2.25 0 1 0 0 4.5 2.25 2.25 0 0 0 0-4.5zM8.07 1a7.07 7.07 0 0 0-7.07 7.07v7.86A7.07 7.07 0 0 0 8.07 23h7.86a7.07 7.07 0 0 0 7.07-7.07V8.07A7.07 7.07 0 0 0 15.93 1H8.07zm11.33 14.93a5.44 5.44 0 0 1-5.43 5.44H8.07a5.44 5.44 0 0 1-5.44-5.44V8.07A5.44 5.44 0 0 1 8.07 2.64h7.86a5.44 5.44 0 0 1 5.44 5.43v7.86z" />
              </svg>
            </a>
          </div>
        </div>
      </div>
      <!-- Copyright -->
      <div class="text-center border-t border-gray-700 mt-6 pt-4">
        <p>&copy; 2024 ReTailor. All rights reserved.</p>
      </div>
    </div>
  </footer>
  